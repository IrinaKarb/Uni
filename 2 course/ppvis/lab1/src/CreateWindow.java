import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CreateWindow {
    public static void pushCheckButton(Button checkButton) {
        checkButton.setSelection(!checkButton.getSelection());
    }
    public static void main(String[] args) {
        final Display display = new Display();
        final Shell shell = new Shell(display, SWT.SHELL_TRIM);
        final FormLayout formLayout = new FormLayout();
        Composite compositeComboBoxAndCo = new Composite(shell, SWT.NONE);
        Composite changeButtons = new Composite(shell, SWT.NONE);
        Composite radioButtons = new Composite(shell, SWT.NONE);
        Composite checkBoxButtons = new Composite(shell, SWT.NONE);
        new CreateCombo().create(shell, compositeComboBoxAndCo);
        new CreateChangingButtons().create(changeButtons, compositeComboBoxAndCo);
        new CreateRadio().create(shell, radioButtons, changeButtons);
        new CreateCheckBox().create(shell, checkBoxButtons, radioButtons);
        new CreateTable().create(shell, checkBoxButtons);
        shell.setLayout(formLayout);
        shell.setText("Interface");
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}