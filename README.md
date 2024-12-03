# Holiday Offers Builder - M1 Project Management Workshop

**Creation of an intelligent mini-system to automatically build holiday offers for a travel destination as part of our Project Management Workshop at CY Tech.**  
The system can be used to manage destination data, search for travel information and create holiday offers (including organised excursions) according to the customer's criteria. The criteria can be, for example, thematic variation, financial cost, comfort, etc.

## Requirements

- At least Java version 11
- Maven (the last version is good)
- Tomcat server version 9 at least (better to have version 9 though)

Eclipse EE or equivalent is required to develop (or be good installing plugins)
> Other IDEs can be used, don't worry

## Technical specifications

- 4-tier architecture
- Languages : JAVA, SQL, LaTeX
- DBMS : MySQL
- Text indexing engine : Lucene
- IDE : Eclipse

## Installation process

1. Clone the project at the right location (it will not move after integrating into Eclipse)
2. Import (described for Eclipse only)
    1. "File" > "Import" > "Existing Maven projects"
    2. Select the wanted folder
    3. At this moment, a ".pom" file should be already selected, you can finish the operation
3. Configuration
    - After importing project, the src/main/java is filled with files ending with ".template" : DO NOT MODIFY THESE !
    - Copy each file and remove the ".template" from the new files's names in order to add personal configurations (like DB creadentials or Lucene index path for example)
    > If need to update an important constant, or add a new one, the template can be altered, but others must be warned afterwards
4. Run the server and see the magnificient result !

## Contributors

Kévin BERNARD, Aldric VITALI SILVESTRE, Nicolas CIBULKA, Quitterie PILON, Enzo KALINOWSKI, Aladdine BEN ROMDHANE
