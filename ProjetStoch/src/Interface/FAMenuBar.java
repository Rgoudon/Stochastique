package Interface;

import javafx.scene.control.*;

public class FAMenuBar extends MenuBar {
    final Menu fileMenu = new Menu("Fichier");
    final MenuItem newItem = new MenuItem("Nouveau...");
    final MenuItem backItem = new MenuItem("Retour");
    final MenuItem quitItem = new MenuItem("Quitter");

    final Menu optionsMenu = new Menu("Options");
    final MenuItem settingsItem = new MenuItem("Réglages");
    final MenuItem exportItem = new MenuItem("Exporter");

    final Menu helpMenu = new Menu("Help");

    final SeparatorMenuItem sep = new SeparatorMenuItem();

    public FAMenuBar() {
        fileMenu.getItems().addAll(newItem, sep, backItem, quitItem);
        optionsMenu.getItems().addAll(exportItem, settingsItem);
        this.getMenus().addAll(fileMenu, optionsMenu, helpMenu);
    }
}
