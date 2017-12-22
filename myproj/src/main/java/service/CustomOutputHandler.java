package service;

import java.io.OutputStream;
import java.util.logging.ConsoleHandler;

public class CustomOutputHandler extends ConsoleHandler {
	public CustomOutputHandler() {
		super();
		setOutputStream(System.out);
	}
	
	@Override
	public synchronized void setOutputStream(OutputStream out) throws SecurityException {
		super.setOutputStream(out);
	}
}
