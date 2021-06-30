package com.haratres.myhr;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.haratres.myhr");

        noClasses()
            .that()
            .resideInAnyPackage("com.haratres.myhr.service..")
            .or()
            .resideInAnyPackage("com.haratres.myhr.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.haratres.myhr.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
