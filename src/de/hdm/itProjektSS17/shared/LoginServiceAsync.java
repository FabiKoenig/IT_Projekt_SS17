package de.hdm.itProjektSS17.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektSS17.client.LoginInfo;

/**
 * 
 * Asynchrones Interface für den LogIn
 *
 */
public interface LoginServiceAsync {
/**
 * 
 * @param requestUri
 * @param callback
 */
	void login(String requestUri, AsyncCallback<LoginInfo> callback);

}
