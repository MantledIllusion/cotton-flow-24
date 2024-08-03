package com.mantledillusion.vaadin.cotton.demo.model.view;

import com.mantledillusion.essentials.expression.Expression;
import com.mantledillusion.vaadin.cotton.component.builder.*;
import com.mantledillusion.vaadin.cotton.demo.IndexView;
import com.mantledillusion.vaadin.cotton.demo.UserView;
import com.mantledillusion.vaadin.cotton.demo.model.ComplexPojo;
import com.mantledillusion.vaadin.cotton.demo.model.SimplePojo;
import com.mantledillusion.vaadin.cotton.demo.security.SecurityConfiguration;
import com.mantledillusion.vaadin.cotton.model.Binding;
import com.mantledillusion.vaadin.cotton.model.ModelContainer;
import com.mantledillusion.vaadin.cotton.view.AbstractView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;

@Route("model")
@AnonymousAllowed
public class ModelView extends AbstractView {

    private final ModelContainer<ComplexPojo> modelContainer = new ModelContainer<ComplexPojo>()
            .setAuditMode(Binding.AuditMode.RESTRICTIVE);

    @Override
    protected Component buildUI() throws Exception {
        return VerticalLayoutBuilder.create()
                .setSizeFull()
                .setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER)
                .add(new UserView())
                .add(HorizontalLayoutBuilder.create()
                        .setHeightFull()
                        .setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER)
                        .add(VerticalLayoutBuilder.create()
                                .setExactWidth(500)
                                .add(HorizontalLayoutBuilder.create()
                                        .add(ButtonBuilder.create()
                                                .setText("Set Model")
                                                .addClickListener(event -> modelContainer.setModel(buildModel()))
                                                .build())
                                        .build())
                                .add(TextFieldBuilder.create()
                                        .bindValue(modelContainer, ComplexPojo.OBJECT.append(SimplePojo.FIELD))
                                            .setAudit(Binding.AccessMode.MASKED, false).setMaskedValue("***")
                                            .setAudit(Binding.AccessMode.READ_ONLY, true)
                                            .setAudit(Binding.AccessMode.READ_WRITE, Expression.of(SecurityConfiguration.Roles.ADMIN))
                                            .bind()
                                        .build())
                                .build())
                        .build())
                .add(RouterLinkBuilder.create()
                        .setText("Back to Index")
                        .setRoute(IndexView.class)
                        .build())
                .build();
    }

    private ComplexPojo buildModel() {
        return new ComplexPojo(new SimplePojo("My model"), List.of());
    }
}
