package business.control;

/**
 * Commands invoker according to Command pattern.
 */
public class ReportInvoker {

    private ReportCommand command;

    public ReportInvoker(ReportCommand command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
