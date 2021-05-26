call runcrud
if "%ERRORLEVEL%" == "0" goto open
echo.
echo GRADLEW BUILD has errors – breaking work
goto fail

:open
start "" "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open URL
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo URL has been opened.