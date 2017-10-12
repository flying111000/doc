'cscript tping.vbs

Set shell = WScript.CreateObject("WScript.Shell")

Set re=New RegExp

re.Pattern = "^Reply|^Request"

Set myping=shell.Exec("ping www.usitrip.com -t")

while Not myping.StdOut.AtEndOfStream

	strLine = myping.StdOut.ReadLine()

	r=re.Test(strLine)

	If r Then

		WScript.Echo date & " "& time & chr(9) & strLine

	End if

Wend