## Línea de comando para compilación

```powershell
javac -encoding UTF-8 -d bin -cp ".;lib/*" @(Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
```


## Linea de comando para Ejecucion 

```powershell
java -cp "bin;lib/*" app.App
```
