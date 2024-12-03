package listeners;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import business.spring.SpringIoC;

/**
 * On application startup, this class will create and fill the index, as well as
 * intializing database connection infos.
 * 
 * @author Aldric Vitali Silvestre
 */
public class StartupListener implements SystemEventListener {
	
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		// 2 cases : post construct and pre destroy
		if (event instanceof PostConstructApplicationEvent) {
			DatabaseInitializer initializer = SpringIoC.getBean(DatabaseInitializer.class);
			initializer.init();
		}
	}

	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof Application;
	}

}
