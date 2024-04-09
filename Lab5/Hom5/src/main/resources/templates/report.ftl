<!DOCTYPE html>
<html>
<head>
    <title>Repository Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Repository Report</h1>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>ID</th>
            </tr>
        </thead>
        <tbody>
            <#list persons as person>
                <tr>
                    <td>${person.name}</td>
                    <td>${person.id}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>
