call runcrud.bat

if "%ERRORLEVELS%" == "0" goto openbrowser
echo.
echo there were some errors running runcrud.bat - breaking work
goto fail

:fail
echo.
echo There where errors.

:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:end
echo.
echo Work is finished

