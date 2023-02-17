# Quarkus Shared Library BOM

## Small Rant
This is a better way to manage dependencies than importing from the parent. You can have multiple BOMs import from a
core BOM (say for something like spring3, spring4, spring5) without having to exclude/include and you can compose 
BOMs together as well. 

Parent imports are harder to manage and override. This project makes a small but deliberate choice to not do that. The
complexity/added overhead of BOM management pays off in larger projects.