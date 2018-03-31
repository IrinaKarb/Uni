import org.eclipse.swt.widgets.*;

public class TickerButton extends Button{
    public TickerButton(Composite parent, int style){
        super(parent, style);
    }
    protected void checkSubclass (){}
    public void runText(Shell shell, Display display, Group group, TickerButton button, String title){
        button.setText(title);
        new Thread(new Runnable() {
            String text = title;
            public void run() {
                while (true) {
                    String string2 = text.substring(text.length() - 1);
                    String string1 = text.substring(0, text.length() - 1);
                    text = string2 + string1;
                    display.getDefault().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            if (!shell.isDisposed()) {
                                button.setText(text);
                            } else if (shell.isDisposed())
                                Thread.currentThread().interrupt();
                        }
                    });
                    try {
                        Thread.currentThread().sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
