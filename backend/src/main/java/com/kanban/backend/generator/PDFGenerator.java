package com.kanban.backend.generator;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.Task;
import com.kanban.backend.model.TaskGroup;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {
    private final Color fontColor = new Color(53, 37, 28);
    private final Color taskGroupColor = new Color(213, 189, 175);
    private final Color taskColor = new Color(245, 235, 224);

    public Document generate(Table table, HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD);
        fontTitle.setSize(20);
        fontTitle.setColor(this.fontColor);

        Paragraph title = new Paragraph(table.getName(), fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(50f);

        document.add(title);

        List<TaskGroup> taskGroups = table.getTaskGroups();

        for (TaskGroup taskGroup: taskGroups) {
            PdfPTable pdfPTable = new PdfPTable(1);
            PdfPCell taskGroupCell = new PdfPCell();
            taskGroupCell.setBackgroundColor(this.taskGroupColor);

            Font taskGroupFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
            taskGroupFont.setSize(14);
            taskGroupFont.setColor(this.fontColor);

            taskGroupCell.setPhrase(new Phrase(taskGroup.getName(), taskGroupFont));
            pdfPTable.addCell(taskGroupCell);

            List<Task> tasks = taskGroup.getTasks();

            for (Task task: tasks) {
                PdfPCell taskCell = new PdfPCell();
                taskCell.setBackgroundColor(this.taskColor);

                Font taskFont = FontFactory.getFont(FontFactory.TIMES);
                taskFont.setSize(24);
                taskFont.setColor(this.fontColor);

                taskCell.setPhrase(new Phrase(task.getName(), taskFont));

                pdfPTable.addCell(taskCell);
            }

            pdfPTable.setSpacingAfter(20.0f);

            document.add(pdfPTable);

        }

        document.close();

        return document;
    }
}
