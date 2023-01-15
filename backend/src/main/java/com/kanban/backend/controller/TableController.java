package com.kanban.backend.controller;

import com.kanban.backend.dto.TableCreatorDTO;
import com.kanban.backend.dto.TableDTO;
import com.kanban.backend.enums.Role;
import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.generator.PDFGenerator;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.model.UserTableRole;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import com.kanban.backend.service.UserTableRoleService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final UserService userService;
    private final UserTableRoleService userTableRoleService;
    private final Mapper mapper;

    @GetMapping("/pdf/{id}")
    public void generatePDF(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=table" + id + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        Table table = this.tableService.getTableById(id);

        if (table == null) {
            throw new ResourceNotFoundException("Table not found!", id);
        }

        PDFGenerator pdfGenerator = new PDFGenerator();
        pdfGenerator.generate(table, response);
    }

    @GetMapping("/tables")
    public ResponseEntity<List<TableDTO>> getAllTables() {
        List<TableDTO> responseBody = tableService
                .getAllTables()
                .stream()
                .map(this.mapper::toTableDTO)
                .toList();

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<TableDTO> getTableById(@PathVariable Long id) {
        TableDTO responseBody = this.mapper.toTableDTO(tableService.getTableById(id));

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/tables")
    public ResponseEntity<TableDTO> addTable(@RequestBody TableCreatorDTO tableCreatorDTO) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("id");
        User owner = this.userService.getUserById(userId);
        Table table = this.mapper.toTable(tableCreatorDTO);
        TableDTO responseBody = this.mapper.toTableDTO(this.tableService.addTable(table));

        this.userTableRoleService.addUserTableRole(new UserTableRole(null, Role.OWNER.name(), owner, table));

        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<TableDTO> deleteTableById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("id");
        UserTableRole userTableRole = this.userTableRoleService.getUserTableRoleByUserIdAndTableId(userId, id);

        if (userTableRole == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (!Objects.equals(userTableRole.getRole(), Role.OWNER.name())) {
            return ResponseEntity.badRequest().body(null);
        }

        TableDTO responseBody = this.mapper.toTableDTO(tableService.deleteTableById(id));

        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("/tables/{id}")
    public ResponseEntity<TableDTO> updateTable(@PathVariable Long id,
                                             @RequestBody Table newTable) {
        TableDTO responseBody = this.mapper.toTableDTO(tableService.updateTable(id, newTable));
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("tables/{tableId}/users/{userId}/roles/{role}")
    public ResponseEntity<TableDTO> assignRoleToTable(@PathVariable Long tableId,
                                                      @PathVariable Long userId,
                                                      @PathVariable Role role) {
        Table table = tableService.getTableById(tableId);
        User user = userService.getUserById(userId);
        UserTableRole userTableRole = new UserTableRole(null, role.name(), user, table);
        List<UserTableRole> userTableRoles = this.userTableRoleService.getAllUserTableRoles();

        for (UserTableRole utr: userTableRoles) {
            if (Objects.equals(utr.getUser().getId(), userId)) {
                return ResponseEntity.badRequest().body(null);
            }

            if (role == Role.OWNER) {
                if (role.name().equals(utr.getRole())) {
                    return ResponseEntity.badRequest().body(null);
                }
            }
        }

        this.userTableRoleService.addUserTableRole(userTableRole);

        TableDTO responseBody = this.mapper.toTableDTO(table);

        return ResponseEntity.ok().body(responseBody);
    }
}
