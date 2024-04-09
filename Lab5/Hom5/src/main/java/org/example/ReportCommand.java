package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a command for generating a report based on the list of persons in the repository.
 */
public class ReportCommand implements Command {
    private final DocumentRepository repository;

    /**
     * Constructs a ReportCommand with the specified repository.
     *
     * @param repository the document repository
     */
    public ReportCommand(DocumentRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the report command with the given arguments.
     *
     * @param args the command arguments
     * @throws CommandException if an error occurs during execution
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != 0) {
            throw new CommandException("Invalid arguments. Usage: report");
        }

        try {
            List<Person> persons = repository.getAllPersons();
            generateReport(persons);
        } catch (IOException | TemplateException e) {
            throw new CommandException("Error generating report: " + e.getMessage());
        }
    }

    private void generateReport(List<Person> persons) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(ReportCommand.class, "/templates");

        Template template = cfg.getTemplate("report.ftl");

        Map<String, Object> data = new HashMap<>();
        data.put("persons", persons);

        File reportFile = new File("report.html");
        Writer out = new FileWriter(reportFile);
        template.process(data, out);
        out.close();

        System.out.println("Report generated successfully: " + reportFile.getAbsolutePath());
    }
}
