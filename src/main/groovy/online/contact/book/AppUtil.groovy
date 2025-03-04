package online.contact.book

import org.grails.web.util.WebUtils

/**
 * Created by amitg on 2/4/2024.
 */
class AppUtil {

    static saveResponse(Boolean isSuccess, def model) {
        return [isSuccess: isSuccess, model: model]
    }

    static getAppSession() {
        return WebUtils.retrieveGrailsWebRequest().session
    }

    static infoMessage(String message, boolean status = true) {
        return [info: message, success: status]
    }
}
