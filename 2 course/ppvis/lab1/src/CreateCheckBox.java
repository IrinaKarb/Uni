import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class CreateCheckBox {
    public void create(Shell shell, Composite checkBoxButtons, Composite radioButtons){
        Group group = new Group(checkBoxButtons, SWT.NONE);
        group.setText("Checkbox Buttons");
        Text text = new Text(group, SWT.BORDER);
        Button checkBoxControllerButton = new Button(group, SWT.PUSH);
        checkBoxControllerButton.setText("Push Button");
        Button checkBox1 = new Button(group, SWT.CHECK);
        checkBox1.setText("1");
        Button checkBox2 = new Button(group, SWT.CHECK);
        checkBox2.setText("2");
        Button checkBox3 = new Button(group, SWT.CHECK);
        checkBox3.setText("3");
        group.setLayout(new FillLayout(SWT.VERTICAL));
        checkBoxButtons.setLayout(new RowLayout(SWT.HORIZONTAL));
        FormData data = new FormData();
        data.left = new FormAttachment(radioButtons, 0);
        checkBoxButtons.setLayoutData(data);
        MessageBox checkError = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK);
        checkError.setText("Warning");
        checkError.setMessage("There's no such checkbox button. Try again.");
        checkBoxControllerButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (text.getText().equals(checkBox1.getText())) {
                    CreateWindow.pushCheckButton(checkBox1);
                }
                else if (text.getText().equals(checkBox2.getText())) {
                    CreateWindow.pushCheckButton(checkBox2);
                }
                else if (text.getText().equals(checkBox3.getText())) {
                    CreateWindow.pushCheckButton(checkBox3);
                }
                else {
                    checkError.open();
                }
            }
        });
    }
}
