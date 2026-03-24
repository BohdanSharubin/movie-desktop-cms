# 🎬 Movie Explorer Desktop App

## 📌 Project Description

**Movie Explorer** is a desktop application built with **Java + JavaFX + PostgreSQL** that allows users to browse, search, and manage movie information.

The application provides functionality for:

* Viewing movie details
* Searching and filtering movies
* Editing and managing movie records
* Working with a relational database

This project is developed as a **team-based student project**.

---

## 🛠️ Tech Stack

* **Language:** Java
* **UI:** JavaFX
* **Database:** PostgreSQL
* **Build Tool:** Maven
* **Version Control:** Git + GitHub
* **CI/CD:** GitHub Actions
* **Documentation:** Javadoc (auto-generated & deployed)

---

## 🚀 Features

* 🔍 Search movies by title, genre, etc.
* 🎞️ View detailed movie information
* ✏️ Edit and update movie data
* ➕ Add new movies
* ❌ Delete movies
* 🗄️ Persistent storage using PostgreSQL

---

## 📚 Use Cases

### 🎬 UC-01: Load Data

**Actor:** User  

**Preconditions:**
- Successful connection to the database is established  

**Main Flow:**
1. User launches the application  
2. The application initializes  
3. Movie data is retrieved from the database  
4. The movie list is displayed in the main window  

**Alternative Flows:**
- **A1: Database connection error**
  - System displays an error notification  
  - Application terminates or runs in limited mode  

**Postconditions:**
- Movie data is displayed  
- OR an error message is shown  

---

### ➕ UC-02: Add Movie

**Actor:** User  

**Preconditions:**
- UC-01 (Load Data) is completed successfully  

**Main Flow:**
1. User clicks the **"Add Movie"** button  
2. The system displays a movie input form  
3. User fills in all required fields  
4. User clicks the **"Save"** button  
5. The system validates input data  
6. If validation succeeds:
   - Movie is saved to the database  
   - Updated movie list is displayed  

**Alternative Flows:**
- **A1: Validation failed**
  - System displays validation error messages  
- **A2: Database error**
  - System displays an error notification  

**Postconditions:**
- Movie is added to the database  
- OR the user is notified about errors  

---

### ✏️ UC-03: Edit Movie

**Actor:** User  

**Preconditions:**
- UC-01 (Load Data) is completed  

**Main Flow:**
1. User selects a movie from the list  
2. User clicks the **"Edit Movie"** button  
3. The system displays a pre-filled form  
4. User modifies desired fields  
5. User clicks the **"Save"** button  
6. The system validates input data  
7. If validation succeeds:
   - Movie data is updated in the database  
   - Updated list is displayed  

**Alternative Flows:**
- **A1: Validation failed**
  - System displays validation errors  
- **A2: Database error**
  - System displays an error notification  

**Postconditions:**
- Movie data is updated  
- OR the user is informed about errors  

---

### ❌ UC-04: Delete Movie

**Actor:** User  

**Preconditions:**
- UC-01 (Load Data) is completed  

**Main Flow:**
1. User selects a movie  
2. User clicks the **"Delete Movie"** button  
3. The system shows a confirmation dialog  
4. User confirms deletion  
5. The system deletes the movie from the database  
6. Updated movie list is displayed  

**Alternative Flows:**
- **A1: User cancels deletion**
  - Operation is aborted  
- **A2: Database error**
  - System displays an error notification  

**Postconditions:**
- Movie is deleted  
- OR no changes are made  

---

### 🔍 UC-05: Search Movies

**Actor:** User  

**Preconditions:**
- UC-01 (Load Data) is completed  

**Main Flow:**
1. User clicks the **"Search Movies"** button  
2. The system displays a search form  
3. User enters search criteria  
4. User clicks the **"Search"** button  
5. The system processes the query  
6. Matching results are displayed  

**Alternative Flows:**
- **A1: No results found**
  - System displays a "No results" message  
- **A2: Database error**
  - System displays an error notification  

**Postconditions:**
- Search results are displayed  
- OR user is notified about issues  

---

## 🧩 UML Class Diagram

> *(UML diagram will be here)*

**Description:**

* Main entities: `Movie`, `User`, `Service`, `Repository` ...
* Separation of concerns:

  * UI layer (JavaFX)
  * Business logic (Service layer)
  * Data access (Repository/DAO layer)

---

## 🗄️ ER Diagram (Database)

> *(ER diagram will be here)*

**Description:**

* Main tables:

  * `movies`
  * `actors`
  * `roles`
* Relationships:

  * One-to-many / many-to-many (depending on design)
* Normalized structure for efficient querying

---

## 👥 Team Members

| Name     | Role         | Responsibilities                        | GitHub |
| -------- | ------------ | --------------------------------------- | ------ |
| Bohdan Sharubin | Team Lead    | Architecture, code review, coordination | [BohdanSharubin](https://github.com/BohdanSharubin) |
| Illia Voronko | Backend Dev  | Database, services, logic               | [VoronkoIllia](https://github.com/VoronkoIllia) |
| Vladislav Ryzantsev | Frontend Dev | JavaFX UI, UX                           | [Nihilistt666](https://github.com/Nihilistt666) |

---

## 📦 Commit Rules

We follow **conventional commits**:

```
feat: add movie search functionality
fix: resolve null pointer in service layer
docs: update README
refactor: improve repository structure
test: add unit tests for MovieService
```

### Rules:

* Use clear and descriptive messages
* One logical change per commit
* English language only
* Reference issues when applicable

---

## ⚙️ CI/CD (GitHub Actions)

On each push:

* ✅ Build project using Maven
* 🧪 Run tests
* 📄 Generate Javadoc
* 🌐 Deploy documentation to GitHub Pages

---

## 📅 Project Timeline

| Date       | Task                         | Status |
| ---------- | ---------------------------- | ------ |
| 2026-03-01 | UI prototype                 | ✅      |
| 2026-03-04 | Database design              | ✅      |
| 2026-03-18 | Design UML class diagram                 | ✅      |
| 2026-03-24 | Project setup                | ✅      |
| YYYY-MM-DD | Core features implementation | ⬜      |
| YYYY-MM-DD | Testing                      | ⬜      |
| YYYY-MM-DD | Final release                | ⬜      |

---

## 📁 Project Structure

```
src/
 ├── main/
 │   ├── java/
 │   │   ├── controller/
 │   │   ├── service/
 │   │   ├── repository/
 │   │   └── model/
 │   └── resources/
 └── test/
```

---

## 📌 Additional Notes

* Follow clean architecture principles
* Keep code modular and readable
* Write tests for core logic
* Document public methods

