package view.administrator;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import view.utils.FileExtension;

/**
 *
 * @author Alex
 */
public class UsersFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        
        if (f.isDirectory()) {
            return true;
        }

        String name = f.getName();
        String extension = FileExtension.getFileExtension(name);
        if (extension == null) {
            return false;
        }
        
        if (extension.equals("garits")) {
            return true;
        }
        
        return false;
    }

    @Override
    public String getDescription() {
        return "Backup files (*.garits)";
    }

}
