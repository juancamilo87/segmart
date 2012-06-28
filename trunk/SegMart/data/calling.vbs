caracteristicas = Wscript.Arguments.Item(0) 
intencion = Wscript.Arguments.Item(1) 
destino = Wscript.Arguments.Item(2) 

if WScript.Arguments.Count <2 then
    WScript.Echo "Missing parameters"
end if

Dim objXL
Set objXL = CreateObject("Excel.Application")
With objXL
    .Workbooks.Open ("data/toCSV.xlsm")
    .Application.Run "toCSV(" & caracteristicas & "," & intencion & "," & destino & ")"
    .Application.Quit
End With
Set objXL = Nothing