package com.contactos.views.nuevocontacto;

import com.contactos.models.Contacto;
import com.contactos.services.ContactoService;
import com.contactos.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Nuevo Contacto")
@Route(value = "nuevo-contacto", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevoContactoView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    TextField tfNombre;
    TextField tfTelefono;
    TextField tfCorreo;
    TextField tfCedula;
    Button btGuardar;
    Button btCancelar;

    String cedula;


    private ContactoService contactoService;

    public NuevoContactoView(ContactoService contactoService) {

        this.contactoService=contactoService;

        HorizontalLayout layoutRow = new HorizontalLayout();
        tfNombre = new TextField();
        tfTelefono = new TextField();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        tfCorreo = new TextField();
        tfCedula = new TextField();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        btGuardar = new Button();
        btCancelar = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        tfNombre.setLabel("Nombre");
        tfNombre.setWidth("min-content");
        tfTelefono.setLabel("Teléfono");
        tfTelefono.setWidth("min-content");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("min-content");
        tfCorreo.setLabel("Correo");
        tfCorreo.setWidth("min-content");
        tfCedula.setLabel("Cédula");
        tfCedula.setWidth("min-content");
        layoutRow4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.setHeight("min-content");
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        btGuardar.setText("Guardar");
        btGuardar.setWidth("min-content");
        btGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        btGuardar.addClickListener(e -> {
            if(cedula!=null){

                String nombre = tfNombre.getValue();
                String telefono = tfTelefono.getValue();
                String correo = tfCorreo.getValue();
                Contacto contacto = new Contacto(nombre,null,telefono,correo);
                contactoService.editarContacto(cedula,contacto);
            }else{

                String cedula1 = tfCedula.getValue();
                String nombre = tfNombre.getValue();
                String telefono = tfTelefono.getValue();
                String correo = tfCorreo.getValue();
                Contacto contacto = new Contacto(nombre,cedula1,telefono,correo);
                contactoService.agregarContacto(contacto);
            }
            btGuardar.getUI().ifPresent(ui ->
                    ui.navigate("lista-contactos"));
                }
        );



        btCancelar.setText("Cancelar");
        btCancelar.setWidth("min-content");

        btCancelar.addClickListener(e -> {
            btCancelar.getUI().ifPresent(ui ->
                    ui.navigate("lista-contactos"));
        });
        getContent().add(layoutRow);
        layoutRow.add(tfNombre);
        layoutRow.add(tfTelefono);
        getContent().add(layoutRow2);
        layoutRow2.add(tfCorreo);
        layoutRow2.add(tfCedula);
        getContent().add(layoutRow4);
        getContent().add(layoutRow3);
        layoutRow3.add(btGuardar);
        layoutRow3.add(btCancelar);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String cedula) {
        this.cedula=cedula;
        if(cedula!=null){
            tfCedula.setEnabled(false);
            Contacto contactoBuscado = contactoService.obtenerPorCedula(cedula);
            tfNombre.setValue(contactoBuscado.getNombre());
            tfCedula.setValue(contactoBuscado.getCedula());
            tfTelefono.setValue(contactoBuscado.getTelefono());
            tfCorreo.setValue(contactoBuscado.getTelefono());
        }else{
            tfCedula.setEnabled(true);
        }
    }
}
