# e-channel-backend
E-Channeling Service
## E-Channeling
This is an e-channeling web application. With the below functionalities.

1. Patients/users can search doctors' availability in various hospitals by choosing a hospital or specialization or doctors name.

2. When the search results are displayed, the user can place an appointment by filling a form.

3. The admins of the system can register the doctors to the system.

4. The admins of the system can create a doctor's session by allocating a date and a time slot, in a particular hospital.

5. Doctors list and channeling time slots are displayed.

So basically, this system is having customer interaction as well as admin interaction functionalities.

## Folder/file structure

Follow the current naming convention and folder structure.

```
E-Channeling
├── client                   // Frontend
|   ├── public               // Public folder
|   ├── src                  // Add all the source files here
| 	|   ├── assets           // Assets folder
| 	|   ├── components       // Components folder. Add all sub components here
|	|   ├── pages            // Main pages folder such as About Page, Landing Page etc.
| 	|   ├── styles           // Styles files
| 	|   ├── stylesVar        // Fixed Styles Library 
|	|   ├── App.tsx          // App.tsx
| 	|   └── index.tsx        // index.tsx file
|   ├── .gitignore           // Gitignore file
|   └── package.json         // node module dependencies
├── e-channel-backend                      //Backend
|       ├── controllers      // Add all controller for models here
└── readme.md                // README file
```
