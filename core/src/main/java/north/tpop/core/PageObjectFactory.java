package north.tpop.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import north.tpop.core.annotation.Path;
import north.tpop.core.annotation.PathType;
import north.tpop.core.annotation.Selectable;
import north.tpop.core.annotation.SelectorType;
import north.tpop.core.elementresolver.AbsoluteElementResolver;
import north.tpop.core.elementresolver.ElementResolver;
import north.tpop.core.elementresolver.RelativeElementResolver;
import north.tpop.core.pathresolver.BasePathResolver;
import north.tpop.core.pathresolver.PathResolver;
import north.tpop.core.pathresolver.RelativePathResolver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObjectFactory {

    private final WebDriver driver;
    private final WaitExecutor waitExecutor;

    public PageObjectFactory(WebDriver driver, WaitExecutor waitExecutor) {
        this.driver = driver;
        this.waitExecutor = waitExecutor;
    }

    public <T> T newPageObject(Class<T> pageObjectClass) {
        try {
            T instance = pageObjectClass.newInstance();

            injectMembers(pageObjectClass, instance);

            return instance;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalStateException("Cannot instantiate class: " + pageObjectClass, ex);
        }
    }

    private void injectMembers(Class<?> clazz, final Object instance) throws SecurityException, InstantiationException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (final Field field : fields) {
            if (field.isAnnotationPresent(Selectable.class)) {
                Object value = newElement(field, instance);
                if (value != null) {
                    injectIntoField(instance, field, value);
                }
            }
        }

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            injectMembers(superclass, instance);
        }
    }

    private Object newElement(final Field field, final Object parent) throws InstantiationException, IllegalAccessException {
        Selectable selectableAnnotation = field.getAnnotation(Selectable.class);
        Path pathAnnotation = field.getAnnotation(Path.class);

        PathType pathType;
        if (parent instanceof Element) {
            pathType = PathType.Relative;
            if (pathAnnotation != null && pathAnnotation.value() == PathType.Absolute) {
                pathType = PathType.Absolute;
            }
        } else {
            if (pathAnnotation != null && pathAnnotation.value() == PathType.Relative) {
                throw new Error("Relative path can't be set for fields fo classes that do not extend Element.");
            }
            pathType = PathType.Absolute;
        }

        By selector = newSelector(selectableAnnotation.by(), selectableAnnotation.value());
        ElementResolver elementResolver;
        if (pathType == PathType.Absolute) {
            elementResolver = new AbsoluteElementResolver(driver, selector);
        } else {
            elementResolver = new RelativeElementResolver((Element) parent, selector);
        }

        PathResolver pathResolver;
        if (parent instanceof Element) {
            pathResolver = new RelativePathResolver((Element) parent, field.getName());
        } else {
            pathResolver = new BasePathResolver(parent.getClass().getSimpleName(), field.getName());
        }

        Class<?> type = field.getType();
        Object newInstance = type.newInstance();
        if (newInstance instanceof Element) {
            Element newElement = (Element) newInstance;

            newElement.driver = driver;
            newElement.elementResolver = elementResolver;
            newElement.pathResolver = pathResolver;
            newElement.waitExecutor = waitExecutor;

            injectMembers(type, newInstance);
        } else if (newInstance instanceof RepeatableElement) {
            @SuppressWarnings("rawtypes")
            RepeatableElement newElement = (RepeatableElement) newInstance;

            newElement.elementResolver = elementResolver;
            newElement.pathResolver = pathResolver;
            newElement.pageObjectFactory = this;

            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

            newElement.clazz = (Class<?>) actualTypeArguments[0];
        } else {
            throw new Error("Only Element or RepeatableElement can have the Selectable annotation.");
        }

        return newInstance;
    }

    private static By newSelector(SelectorType selectorType, String selector) {
        switch (selectorType) {
            case className:
                return By.className(selector);
            case cssSelector:
                return By.cssSelector(selector);
            case id:
                return By.id(selector);
            case linkText:
                return By.linkText(selector);
            case name:
                return By.name(selector);
            case partialLinkText:
                return By.partialLinkText(selector);
            case tagName:
                return By.tagName(selector);
            case xpath:
                return By.xpath(selector);
        }
        return null;
    }

    private static void injectIntoField(final Object instance, final Field field, final Object value) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {

            @Override
            public Object run() {
                boolean wasAccessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    field.set(instance, value);
                    return null;
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    throw new IllegalStateException("Cannot set field: " + field, ex);
                } finally {
                    field.setAccessible(wasAccessible);
                }
            }
        });
    }

}
