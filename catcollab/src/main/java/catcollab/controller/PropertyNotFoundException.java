package catcollab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException(String property, String model) {
        super("could not find " + model + " with the key:  " + property + " .");
    }
}
