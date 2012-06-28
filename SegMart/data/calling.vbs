Prueba

Sub Prueba()
If WScript.Arguments.Count <3 then
    WScript.Echo "Missing parameters"
end if
Dim caracteristicas
Dim intencion
Dim destino
caracteristicas = Wscript.Arguments.Item(0) 
intencion = Wscript.Arguments.Item(1) 
destino = Wscript.Arguments.Item(2)
xlsm = Wscript.Arguments.Item(3)



Dim objXL

Set objXL = CreateObject("Excel.Application")

With objXL
    .Workbooks.Open (xlsm & "/toCSV.xlsm")
    .Run "Module1.toCSV" , Chr(34) & caracteristicas & Chr(34), Chr(34) & intencion & Chr(34) ,Chr(34) & destino & Chr(34)
    
    .Application.Quit
End With
Set objXL = Nothing
WScript.Echo "Macro terminada"
End Sub 