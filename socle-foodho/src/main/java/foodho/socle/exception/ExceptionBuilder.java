package foodho.socle.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public abstract class ExceptionBuilder<T extends Exception> {

  @Getter
  private List<T> exceptions = new ArrayList();
  private Class<T> exceptionClass;


  public ExceptionBuilder(Class<T> exceptionClass) {
    this.exceptionClass = exceptionClass;
  }

  /**
   * Méthode permettant d'ajouter une nouvelle exception au builder
   *
   * @param message
   */
  public void addException(String message) {
    T exception = null;
    try {
      Constructor<T> constructor = this.exceptionClass.getConstructor(String.class);
      exception = constructor.newInstance(message);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    this.exceptions.add(exception);
  }

  /**
   * @param message
   * @param throwable
   */
  public void addException(String message, Throwable throwable) {
    T exception = null;
    try {
      Constructor<T> constructor = this.exceptionClass.getConstructor(String.class, Throwable.class);
      exception = constructor.newInstance(message, throwable);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    this.exceptions.add(exception);
  }

  /**
   * Méthode permettant de vider la liste des arguments du builder
   */
  public void clear() {
      log.info("ExceptionBuilder in cleaning...");
    this.exceptions.clear();
  }

  /**
   * Méthode permettant de construire une exception avec un message résumant les différentes erreurs de la liste des exceptions
   *
   * @return
   */
  public T buildException() {

    T exception = null;

    StringBuilder exceptionMessageBuilder = new StringBuilder();
    String separator = "";

    for (T t : this.exceptions) {
      final String message = t.getMessage();
      final String causeMessage = t.getCause().getMessage();
      String formattedMessage = MessageFormat.format("{0} ( {1} ) {2}", message, causeMessage, separator);
      exceptionMessageBuilder.append(formattedMessage);
      separator = MessageFormat.format("{0}", "\n");
    }

    try {
      Constructor<T> constructor = exceptionClass.getConstructor(String.class);
      exception = constructor.newInstance(exceptionMessageBuilder.toString());
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    this.clear();

    return exception;
  }

  /**
   * Méthode permettant de construire une exception avec un message résumant les différentes erreurs de la liste des exceptions
   *
   * @return
   */
  public T  buildException(String message, Throwable t) {

    T exception = null;

    try {
      Constructor<T> constructor = exceptionClass.getConstructor(String.class, Throwable.class);
      exception = constructor.newInstance(message, t);
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    this.clear();

    return exception;
  }

  /**
   * Méthode permettant de lancer l'exception finale
   *
   * @throws T
   */
  public boolean throwException() throws T {
    if (this.hasException()) throw this.buildException();
    return false;
  }

  /**
   * Méthode permettant de savoir si le builder a des exceptions
   *
   * @return
   */
  public boolean hasException() {
    return !this.exceptions.isEmpty();
  }

  /**
   * Metode permettant de vérifier qu'il y aucune exception
   *
   * @return
   */
  public boolean isEmpty() {
    return this.exceptions.isEmpty();
  }
}
