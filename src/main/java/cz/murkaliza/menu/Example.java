package cz.murkaliza.menu;

public class Example {

    private static final TextMenuItem item1 = new TextMenuItem("Item 1", new Runnable() {
        public void run() {
            System.out.println("Running Item 1");
        }
    });

    private static final TextMenuItem item2 = new TextMenuItem("Item 2", new Runnable() {
        public void run() {
            System.out.println("Running Item 2");
        }
    });

    private static final TextMenuItem item3 = new TextMenuItem("Item 3", new Runnable() {
        public void run() {
            System.out.println("Running Item 3");
        }
    });

    private static TextMenu nestedMenu = new TextMenu(
            "Nested menu", true, false,
            item2, item3);
    private static TextMenu topMenu = new TextMenu(
            "Top menu", false, true,
            item1, nestedMenu);
    public static void main(String... args) {
        topMenu.run();
    }
}
