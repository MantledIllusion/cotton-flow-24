# Cotton 3.x (Vaadin 24)
Cotton is a Vaadin extension destined for the ultimate of developer convenience.

It is available as a maven dependency through [mvnrepository.com/cotton](https://mvnrepository.com/artifact/com.mantledillusion.vaadin/cotton) as follows:

```xml
<dependency>
    <groupId>com.mantledillusion.vaadin</groupId>
    <artifactId>{your package}</artifactId>
    <version>{your version}</version>
</dependency>
```

## Which version of Cotton should I use?

You can choose Cotton's major version depending on the Vaadin version you are planning to work with:

|                          Vaadin 8                           |                            Vaadin 14                            | Vaadin 24  |
|:-----------------------------------------------------------:|:---------------------------------------------------------------:|:----------:|
| [Cotton 1.x](https://github.com/MantledIllusion/cotton-gwt) | [Cotton 2.x](https://github.com/MantledIllusion/cotton-flow-14) | Cotton 3.x |

Make sure to get the highest minor/bugfix version available within your selected major version of Cotton, not only to profit from the newest functions and fixed, but also to use the most up-to-date version of Vaadin.

## Which package should I choose?
Cotton is made up of modularized maven artifacts which closely align with the modules Vaadin provides itself.  Each artifact can by used by itself, depending on the project you are working on.

### For Vaadin Applications
Choose one of the following packages if you are planning to build a full application using cotton as a base, or exchange the Vaadin corresponding dependency in your pre-existing application to extend it with Cotton:

| Application               |       Cotton Package       |       Vaadin Package       |
|---------------------------|:--------------------------:|:--------------------------:|
| Vaadin 24                 |           cotton           |        vaadin-core         |
| Vaadin 24 + Spring 6      |       cotton-spring        |       vaadin-spring        |
| Vaadin 24 + Spring Boot 3 | cotton-spring-boot-starter | vaadin-spring-boot-starter |

Cotton also offers additional extensions for the packages above to use for specific use cases:

| Use Case                  |     Cotton Package      |         3rd Party Library         |
|---------------------------|:-----------------------:|:---------------------------------:|
| Extended Responsiveness   | cotton-responsive-yauaa | [YAUAA](https://yauaa.basjes.nl/) |

### For Vaadin Libraries
If you are creating libraries yourself and only require Cotton for a specific part of Vaadin, you can select one of Cotton's low-level artifacts:

| Use Case                            |          Cotton Package           |        Vaadin Package        |
|-------------------------------------|:---------------------------------:|:----------------------------:|
| General Vaadin Platform Extension   |            cotton-core            |         vaadin-core          |
| Specific Vaadin Component Extension | cotton-component-[component-name] | vaadin-[component-name]-flow |
| Custom Functional Vaadin Component  |       cotton-component-base       | vaadin-flow-components-base  |
| Custom Data Vaadin Component        |       cotton-component-data       |          flow-data           |
| Data Binding Library                |            cotton-data            |          flow-data           |
| Localization Extension              |            cotton-i18n            |         flow-server          |
| Other Low-Level Extension           |           cotton-server           |         flow-server          |
