if WScript.Arguments.Count <3 then
    WScript.Echo "Missing parameters"
    Exit
end if

caracteristicas = Wscript.Arguments.Item(0) 
intencion = Wscript.Arguments.Item(1) 
destino = Wscript.Arguments.Item(2)
xlsm = Wscript.Arguments.Item(3)



Dim objXL
Set objXL = CreateObject("Excel.Application")
With objXL
    .Workbooks.Open (xlsm & "/toCSV.xlsm")
    .Application.Run "toCSV(" & caracteristicas & "," & intencion & "," & destino & ")"
    .Application.Quit
End With
Set objXL = Nothing