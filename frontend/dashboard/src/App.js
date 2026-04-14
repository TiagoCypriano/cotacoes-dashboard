import { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement,
  Tooltip,
  Legend
} from "chart.js";

ChartJS.register(
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement,
  Tooltip,
  Legend
);

function App() {
  const [dados, setDados] = useState([]);

  const carregarDados = async () => {
    try {
      const res = await fetch("https://cotacoes-dashboard-production.up.railway.app/cotacoes");
      const json = await res.json();
      setDados(json);
    } catch (erro) {
      console.error("Erro:", erro);
    }
  };

  useEffect(() => {
    carregarDados();
    const intervalo = setInterval(carregarDados, 60000);
    return () => clearInterval(intervalo);
  }, []);

  const bitcoinData = dados.filter(d => d.moeda === "bitcoin").slice(-10);
  const ethereumData = dados.filter(d => d.moeda === "ethereum").slice(-10);

  const bitcoin = bitcoinData.map(d => d.valor);
  const ethereum = ethereumData.map(d => d.valor);

  const labels = bitcoinData.map(d =>
    new Date(d.dataHora).toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit"
    })
  );

  const ultimoBTC = bitcoin[bitcoin.length - 1];
  const anteriorBTC = bitcoin[bitcoin.length - 2];

  const ultimoETH = ethereum[ethereum.length - 1];
  const anteriorETH = ethereum[ethereum.length - 2];

  const variacaoBTC = anteriorBTC
    ? ((ultimoBTC - anteriorBTC) / anteriorBTC) * 100
    : 0;

  const variacaoETH = anteriorETH
    ? ((ultimoETH - anteriorETH) / anteriorETH) * 100
    : 0;

  const corBTC = variacaoBTC >= 0 ? "#22c55e" : "#ef4444";
  const corETH = variacaoETH >= 0 ? "#22c55e" : "#ef4444";

  const data = {
    labels,
    datasets: [
      {
        label: "Bitcoin",
        data: bitcoin,
        borderColor: "#f59e0b",
        tension: 0.4
      },
      {
        label: "Ethereum",
        data: ethereum,
        borderColor: "#8b5cf6",
        tension: 0.4
      }
    ]
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        labels: { color: "#fff" }
      },
      tooltip: {
        callbacks: {
          label: function (context) {
            return (
              context.dataset.label +
              ": R$ " +
              context.raw.toLocaleString("pt-BR", {
                minimumFractionDigits: 2
              })
            );
          }
        }
      }
    },
    scales: {
      x: { ticks: { color: "#aaa" } },
      y: { ticks: { color: "#aaa" } }
    }
  };

  if (bitcoin.length === 0) {
    return <h2 style={{ color: "white" }}>Carregando...</h2>;
  }

  return (
    <div style={{
      background: "#0f172a",
      minHeight: "100vh",
      padding: "30px",
      fontFamily: "Arial"
    }}>
      <h1 style={{ color: "white", textAlign: "center" }}>
        📊 Crypto Dashboard
      </h1>

      {/* CARDS */}
      <div style={{
        display: "flex",
        justifyContent: "center",
        gap: "20px",
        marginTop: "30px",
        flexWrap: "wrap"
      }}>
        {/* BTC */}
        <div style={{
          background: "#1e293b",
          padding: "20px",
          borderRadius: "15px",
          width: "220px",
          boxShadow: "0 10px 20px rgba(0,0,0,0.3)"
        }}>
          <h3 style={{ color: "#f59e0b" }}>Bitcoin</h3>
          <p style={{ color: "white", fontSize: "20px" }}>
            R$ {ultimoBTC.toLocaleString("pt-BR")}
          </p>
          <p style={{ color: corBTC }}>
            {variacaoBTC.toFixed(2)}%
          </p>
        </div>

        {/* ETH */}
        <div style={{
          background: "#1e293b",
          padding: "20px",
          borderRadius: "15px",
          width: "220px",
          boxShadow: "0 10px 20px rgba(0,0,0,0.3)"
        }}>
          <h3 style={{ color: "#8b5cf6" }}>Ethereum</h3>
          <p style={{ color: "white", fontSize: "20px" }}>
            R$ {ultimoETH.toLocaleString("pt-BR")}
          </p>
          <p style={{ color: corETH }}>
            {variacaoETH.toFixed(2)}%
          </p>
        </div>
      </div>

      {/* GRÁFICO */}
      <div style={{
        maxWidth: "900px",
        margin: "40px auto",
        background: "#1e293b",
        padding: "20px",
        borderRadius: "15px",
        boxShadow: "0 10px 20px rgba(0,0,0,0.3)"
      }}>
        <Line data={data} options={options} />
      </div>
    </div>
  );
}

export default App;