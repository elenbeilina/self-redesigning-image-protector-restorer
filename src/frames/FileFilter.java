package frames;

import java.io.File;

class FileFilter extends javax.swing.filechooser.FileFilter {
	
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		return isPngExtension(f);
	}
	
	@Override
	public String getDescription() {
		return "Images";
	}
	
	public static boolean isPngExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		
		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		
		if (ext != null) {
			return ext.equalsIgnoreCase("png");
		}
		return false;
	}
	
	public static File addExtension(File f) {
		if (!FileFilter.isPngExtension(f)) {
			String n = f.getName().concat(".png");
			f = new File(f.getParentFile(), n);
		}
		return f;
	}
	
}
