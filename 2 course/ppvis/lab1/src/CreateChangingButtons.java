import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class CreateChangingButtons {
    public void create(Composite changeButtons, Composite compositeComboBoxAndCo) {
        Group group = new Group(changeButtons, SWT.NONE);
        group.setText("Changing Buttons");
        FormData data = new FormData();
        data.left = new FormAttachment(compositeComboBoxAndCo, 0);
        group.setLayout(new FillLayout(SWT.VERTICAL));
        changeButtons.setLayout(new RowLayout(SWT.HORIZONTAL));
        changeButtons.setLayoutData(data);
        Text text = new Text(group, SWT.BORDER);
        Button firstButton = new Button(group, SWT.PUSH);
        firstButton.setText("To second button");
        Button secondButton = new Button(group, SWT.PUSH);
        secondButton.setText("To first button");
        firstButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(org.eclipse.swt.widgets.Event event) {
                if (text.getText().equals(""))
                    secondButton.setText(firstButton.getText());
                else
                    secondButton.setText(text.getText());
            }
        });
        secondButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(org.eclipse.swt.widgets.Event event) {
                if (text.getText().equals(""))
                    firstButton.setText(secondButton.getText());
                else
                    firstButton.setText(text.getText());
            }
        });
    }
}
