package com.mantledillusion.vaadin.cotton.component.builder;

import com.mantledillusion.vaadin.cotton.component.ConfigurationBuilder;
import com.mantledillusion.vaadin.cotton.component.mixin.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

/**
 * {@link ConfigurationBuilder} for {@link Image}s.
 */
public class ImageBuilder extends AbstractClickableHtmlContainerBuilder<Image, ImageBuilder> {

    private ImageBuilder() {
        super(Image::new);
    }

    /**
     * Factory method for a new instance.
     *
     * @return A new instance, never null.
     */
    public static ImageBuilder create() {
        return new ImageBuilder();
    }

    /**
     * Builder method, configures the {@link Image}'s source as an image available over the web.
     *
     * @see Image#setSrc(String)
     * @param src
     *            The URL of the image; might <b>not</b> be null.
     * @return this
     */
    public ImageBuilder setSrc(String src) {
        return configure(image -> image.setSrc(src));
    }

    /**
     * Builder method, configures the {@link Image}'s source as a streamable resource.
     *
     * @see Image#setSrc(AbstractStreamResource)
     * @param src
     *            The resource stream of the image; might <b>not</b> be null.
     * @return this
     */
    public ImageBuilder setSrc(AbstractStreamResource src) {
        return configure(image -> image.setSrc(src));
    }

    /**
     * Builder method, configures the {@link Image}'s source as a classpath resource.
     *
     * @see Image#setSrc(AbstractStreamResource)
     * @param resourceClass
     *            The class to retrieve the resource from; might <b>not</b> be null.
     * @param src
     *            The classpath to find the image under; might <b>not</b> be null.
     * @return this
     */
    public ImageBuilder setSrc(Class<?> resourceClass, String src) {
        return configure(image -> image.setSrc(new StreamResource(src.replace('/', '.'), () ->
                resourceClass.getClassLoader().getResourceAsStream(src))));
    }

    /**
     * Builder method, configures the {@link Image}'s alternative text.
     *
     * @see Image#setAlt(String)
     * @param alt
     *            The alternative text; might be null.
     * @return this
     */
    public ImageBuilder setAlt(String alt) {
        return configure(image -> image.setAlt(alt));
    }
}
