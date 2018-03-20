import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class CreateRadio {
    public void create(Shell shell, Composite radioButtons, Composite changeButtons) {
        Group group = new Group(radioButtons, SWT.NONE);
        group.setText("Radio Buttons");
        Text text = new Text(group, SWT.BORDER);
        Button radioControllerButton = new Button(group, SWT.PUSH);
        radioControllerButton.setText("Push Button");
        Button radioButton1 = new Button(group, SWT.RADIO);
        radioButton1.setText("1");
        Button radioButton2 = new Button(group, SWT.RADIO);
        radioButton2.setText("2");
        Button radioButton3 = new Button(group, SWT.RADIO);
        radioButton3.setText("3");
        group.setLayout(new FillLayout(SWT.VERTICAL));
        radioButtons.setLayout(new RowLayout(SWT.HORIZONTAL));
        FormData data = new FormData();
        data.left = new FormAttachment(changeButtons, 0);
        radioButtons.setLayoutData(data);
        MessageBox radioError = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK);
        radioError.setText("Warning");
        radioError.setMessage("There's no such radio button. Try again.");
        radioControllerButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (text.getText().equals(radioButton1.getText())) {
                    radioButton1.setSelection(true);
                    radioButton2.setSelection(false);
                    radioButton3.setSelection(false);
                }
                else if (text.getText().equals(radioButton2.getText())) {
                    radioButton2.setSelection(true);
                    radioButton1.setSelection(false);
                    radioButton3.setSelection(false);
                }
                else if (text.getText().equals(radioButton3.getText())) {
                    radioButton3.setSelection(true);
                    radioButton2.setSelection(false);
                    radioButton1.setSelection(false);
                }
                else {
                    radioError.open();
                }
            }
        });
    }
}
