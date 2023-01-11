package com.kanban.backend.controller;

import com.kanban.backend.generator.PDFGenerator;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final UserService userService;

    @GetMapping("/pdf/{id}")
    public void generatePDF(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=table" + id + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        Table table = this.tableService.getTableById(id);

        PDFGenerator pdfGenerator = new PDFGenerator();
        pdfGenerator.generate(table, response);
    }

    @GetMapping("/tables")
    public List<Table> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/tables/{id}")
    public Table getTableById(@PathVariable Long id) {
        return tableService.getTableById(id);
    }

    @PostMapping("/tables")
    public Table addTable(@RequestBody Table table) {
        return tableService.addTable(table);
    }

    @DeleteMapping("/tables/{id}")
    public void deleteTableById(@PathVariable Long id) {
        tableService.deleteTableById(id);
    }

    @PutMapping("tables/{tableId}/users/{userId}")
    public Table assignOwnerToTable(@PathVariable Long tableId, @PathVariable Long userId) {
        Table table = tableService.getTableById(tableId);
        User owner = userService.getUserById(userId);

        table.setOwner(owner);
        return tableService.addTable(table);
    }
}
