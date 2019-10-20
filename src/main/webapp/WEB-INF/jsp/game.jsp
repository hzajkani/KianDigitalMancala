<html>
<head>
    <title>Mancala</title>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="/css/main.css"
          rel="stylesheet">
</head>

<body style="align-self: center; alignment: center; align-items: center; align-items: center">
<br>
<nav>

    <h3 class="h3" style="padding-left: 15px; background-color: #8fd19e">
        Mancala Kian Game
    </h3>
</nav>

<div class="container" style="align: center;">
    <h4>Game status: <label id="gameStatus">${mancala.status}</label></h4>

    <h4>Turn: <label id="turn">${mancala.turn}</label></h4>
    <h4></h4>
    <h4>Winner: <label id="gameRes"> ${mancala.gameResult}</label></h4>
    <h4></h4>

    <table border="0" class="tableMancala">
        <tbody>
        <tr align="center">
            <td rowspan="3" style="width: 60px">
                <div id="pit6"
                     class="button1">${mancala.board.player1BigPit.stoneCount}</div>
            </td>

            <td>
                <button id="pit5" type='button' class="button1"
                        onclick='take(${mancala.id}, 5)'>${mancala.board.pits[5].stoneCount}</button>
            </td>
            <td>
                <button id="pit4" type='button' class="button1"
                        onclick='take(${mancala.id}, 4)'>${mancala.board.pits[4].stoneCount}</button>
            </td>
            <td>
                <button id="pit3" type='button' class="button1"
                        onclick='take(${mancala.id}, 3)'>${mancala.board.pits[3].stoneCount}</button>
            </td>
            <td>
                <button id="pit2" type='button' class="button1"
                        onclick='take(${mancala.id}, 2)'>${mancala.board.pits[2].stoneCount}</button>
            </td>
            <td>
                <button id="pit1" type='button' class="button1"
                        onclick='take(${mancala.id}, 1)'>${mancala.board.pits[1].stoneCount}</button>
            </td>
            <td>
                <button id="pit0" type='button' class="button1"
                        onclick='take(${mancala.id}, 0)'>${mancala.board.pits[0].stoneCount}</button>
            </td>

            <td rowspan="3" style="width: 60px">
                <div id="pit13"
                     class="button2">${mancala.board.player2BigPit.stoneCount}</div>
            </td>
        </tr>
        <tr align="center">
            <td colspan="6" style="height: 40px;"></td>
        </tr>
        <tr align="center">
            <td>
                <button id="pit7" type='button' class="button2"
                        onclick='take(${mancala.id}, 7)'>${mancala.board.pits[7].stoneCount}</button>
            </td>
            <td>
                <button id="pit8" type='button' class="button2"
                        onclick='take(${mancala.id}, 8)'>${mancala.board.pits[8].stoneCount}</button>
            </td>
            <td>
                <button id="pit9" type='button' class="button2"
                        onclick='take(${mancala.id}, 9)'>${mancala.board.pits[9].stoneCount}</button>
            </td>
            <td>
                <button id="pit10" type='button' class="button2"
                        onclick='take(${mancala.id}, 10)'>${mancala.board.pits[10].stoneCount}</button>
            </td>
            <td>
                <button id="pit11" type='button' class="button2"
                        onclick='take(${mancala.id}, 11)'>${mancala.board.pits[11].stoneCount}</button>
            </td>
            <td>
                <button id="pit12" type='button' class="button2"
                        onclick='take(${mancala.id}, 12)'>${mancala.board.pits[12].stoneCount}</button>
            </td>
        </tr>
        </tbody>
    </table>

    <h3 style="color: maroon" id="message"> Description : </h3>
</div>

<button class="restartBtn" onclick="window.location.href = '/';">Restart Game</button>
<br>
<br>

<nav>

    <h3 class="h3" style="padding-left: 15px; background-color: #8fd19e">
        By : Hamid Zajkani
    </h3>
</nav>
<script>
    var updatePit = function (pitDomId, newVal) {
        document.getElementById(pitDomId).innerHTML = newVal;
    }

    var success = function (data) {
        var obj = JSON.parse(data);

        if (obj["status"] == "OK") {
            var i;
            for (i = 0; i < obj["mancala"]["board"]["pitCount"]; i++) {
                updatePit("pit" + i, obj["mancala"]["board"]["pits"][i]["stoneCount"]);
            }

            document.getElementById("turn").innerHTML = obj["mancala"]["turn"];
            document.getElementById("gameRes").innerHTML = obj["mancala"]["gameResult"];
            document.getElementById("gameStatus").innerHTML = obj["mancala"]["status"];
            document.getElementById("message").innerHTML = "Description : ";
        } else {
            document.getElementById("message").innerHTML = "Description : " + obj["message"];
        }
    }

    var take = function (gameId, pitId) {
        document.getElementById("message").innerHTML = "";
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                success(this.responseText);
            }
        };
        xhttp.open("PUT", "/games/" + gameId + "/pits/" + pitId, true);
        xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhttp.send();
    }
</script>

</body>
</html>