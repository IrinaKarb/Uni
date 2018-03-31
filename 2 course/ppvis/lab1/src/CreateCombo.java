import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;


public class CreateCombo {
    public void create(Shell shell, Composite compositeComboBoxAndCo) {
        Group group = new Group(compositeComboBoxAndCo, SWT.NONE);
        group.setText("ComboBox");
        MessageBox messageBox = new MessageBox(shell, SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("Warning");
        messageBox.setMessage("You have already input this text in your ComboBox. Input something else.");
        group.setLayout(new FillLayout(SWT.VERTICAL));
        compositeComboBoxAndCo.setLayout(new RowLayout(SWT.VERTICAL));
        Text text = new Text(group, SWT.BORDER);
        Button submitButton = new Button(group, SWT.PUSH);
        Combo comboBox = new Combo(group, SWT.READ_ONLY | SWT.SINGLE);
        submitButton.setText("Submit");
        submitButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (comboBox.getItemCount() == 0) {
                    comboBox.add(text.getText());
                }
                else
                    for (int i = 0; i < comboBox.getItemCount(); i++) {
                        if (text.getText().equals(comboBox.getItem(i))) {
                            messageBox.open();
                            break;
                        }
                        comboBox.add(text.getText());
                        break;
                    }
                    text.setText("");
            }
        });
    }
}
