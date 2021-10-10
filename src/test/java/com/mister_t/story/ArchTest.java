package com.mister_t.story;

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
            .importPackages("com.mister_t.story");

        noClasses()
            .that()
            .resideInAnyPackage("com.mister_t.story.service..")
            .or()
            .resideInAnyPackage("com.mister_t.story.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mister_t.story.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
