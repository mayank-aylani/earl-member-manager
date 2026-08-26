<div><img src="images/1.png" width="120%"></div>
<div><img src="images/2.png" width="120%"></div>
CLIMemberManager — A Database-less Member Management CLI (Java)
> A lightweight command-line member management system that stores data with **no relational database** — using Java's `RandomAccessFile` for persistent, file-based storage.
About
`CLIMemberManager` is a Java command-line application for managing member registration records. Instead of MySQL or any external storage engine, it uses `RandomAccessFile` to read and write records directly to a flat data file — a deliberate exercise in understanding low-level file I/O and persistence without an ORM or database.
It supports full CRUD, input validation, search, and aggregate statistics, all driven by command-line arguments.
Tech used
Java File I/O — `RandomAccessFile`
Command-line argument processing
Exception handling and input validation
Modular, method-per-operation design
Features
CRUD: add, update, remove, and retrieve member records.
Fields: contact number, member name, enrolled course, registration fee.
Validation:
rejects duplicate contact numbers
accepts only supported courses — `C`, `C++`, `Java`, `J2EE`, `Python`
enforces integer fees and handles bad arguments gracefully
Search: by contact number, or by enrolled course.
Aggregates: total registrations and total fee collected.
Documentation & usage
The first CLI argument is the operation; the rest are its parameters.
```bash
# add <contact> <name> <course> <fee>
java MemberManager add 9998887776 "Asha Rao" Java 15000

# update an existing member (matched by contact number)
java MemberManager update 9998887776 "Asha R." J2EE 18000

# remove
java MemberManager remove 9998887776

# retrieve
java MemberManager getAll
java MemberManager getByContactNumber 9998887776
java MemberManager getByCourse Java
```
Valid operations: `add`, `update`, `remove`, `getAll`, `getByContactNumber`, `getByCourse`
Valid courses: `C`, `C++`, `Java`, `J2EE`, `Python`
Records persist to `member.data` in the working directory.
Design notes
No database by design — records are serialized as lines and navigated with the file pointer via `RandomAccessFile`, so persistence survives restarts without any external dependency.
Fail-safe input — invalid operations, missing arguments, unsupported courses, and non-integer fees are all caught and reported clearly instead of crashing.
What I learned
Practical Java file handling with `RandomAccessFile`, designing a clean CLI contract, defensive validation, exception handling, and how persistence can work without a database at all.
Status
Complete for the core feature set; open to extending with export/import and richer queries.
---
Built by Mayank Aylani to master Java file I/O and CLI application design.
