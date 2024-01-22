package cz.murkaliza.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextMenu extends TextMenuItem {

    private static final TextMenuItem quit = new TextMenuItem("Quit", new Runnable() {
        public void run() {
            System.exit(0);
        }
    });

    private static final TextMenuItem back = new TextMenuItem("Back");

    List<TextMenuItem> items;

    public TextMenu(String title, boolean addBack, boolean addQuit, TextMenuItem ... items) {
        super(title);
        setExec(this);
        initialize(addBack, addQuit, items);
    }

    private void initialize(boolean addBack, boolean addQuit, TextMenuItem ... items) {
        this.items = new ArrayList<TextMenuItem>(Arrays.asList(items));
        if (addBack) this.items.add(back);
        if (addQuit) this.items.add(quit);
    }

    private void display() {
        int option = 0;
        System.out.println();
        System.out.println(getTitle() + ":");
        for (TextMenuItem item : items) {
            System.out.println((option++) + ": " + item.getTitle());
        }
        System.out.print("Select option: ");
        System.out.flush();
    }

    private TextMenuItem prompt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            display();
            String line = br.readLine();
            try {
                int option= Integer.parseInt(line);
                if (option >= 0 && option < items.size())
                    return items.get(option);
            }
            catch (NumberFormatException e) { }
            System.out.println("Not a valid menu option: " + line);
        }
    }

    public void run() {
        try {
            TextMenuItem item = prompt();
            while (item.isExec()) {
                item.run();
                item = prompt();
            }
        }
        catch (Throwable t) {
            t.printStackTrace(System.out);
        }
    }
}