package com.base.pagefactory ;


/**
 * @apiNote when a class cannot be constructed because of a missing default constructor
 */
@SuppressWarnings("serial")
class MissingDefaultConstructorException extends RuntimeException {

  MissingDefaultConstructorException(String message, Throwable cause) {
    super(message, cause);
  }
}
