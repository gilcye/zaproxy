/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.zaproxy.zap.extension.keyboard;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class KeyboardShortcut {

    private String name;
    private String identifier;
    private KeyStroke keyStroke;
    private boolean changed = false;

    public KeyboardShortcut() {
    }

    public KeyboardShortcut(String identifier, String name, KeyStroke keyStroke) {
    	this.identifier = identifier;
    	this.name = name;
    	this.keyStroke = keyStroke;
    }

    public String getName() {
    	return this.name;
    }
    
    public String getIdentifier() {
    	return this.identifier;
    	
    }

    public KeyStroke getKeyStroke() {
    	return keyStroke;
    }

    public String getKeyStrokeKeyCodeString() {
    	if (this.keyStroke == null) {
    		return "";
    	}
		int keyCode = this.keyStroke.getKeyCode();
		if (keyCode >= KeyEvent.VK_F1 && keyCode <= KeyEvent.VK_F12) {
			// Function key
			return "F" + (keyCode - KeyEvent.VK_F1 + 1 ); 
		} else {
			// A 'normal' key
			return String.valueOf((char)keyCode).toUpperCase();
		}
    }

    public String getKeyStrokeModifiersString() {
    	if (this.keyStroke == null) {
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	
    	if ((keyStroke.getModifiers() & InputEvent.CTRL_DOWN_MASK) > 0) {
    		sb.append("Control ");
    	}
    	if ((keyStroke.getModifiers() & InputEvent.ALT_DOWN_MASK) > 0) {
    		sb.append("Alt ");
    	}
    	if ((keyStroke.getModifiers() & InputEvent.SHIFT_DOWN_MASK) > 0) {
    		sb.append("Shift ");
    	}
		return sb.toString();
    }

    public String getKeyStrokeString() {
    	if (this.keyStroke == null) {
    		return "";
    	}
		return getKeyStrokeModifiersString() + " " + getKeyStrokeKeyCodeString();
    }
    
    public void setKeyStroke(KeyStroke keyStroke) {
    	this.keyStroke = keyStroke;
    	this.changed = true;
    }

	public boolean isChanged() {
		return changed;
	}
}
