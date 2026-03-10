<template>
  <div class="home">
    <div class="hero">
      <h1 class="hero-title">
        <span class="gradient-text">Otimize sua Produção</span>
      </h1>
      <p class="hero-subtitle">Maximize seus lucros com inteligência artificial e algoritmos avançados</p>
    </div>
    
    <div class="features">
      <div class="feature-card">
        <div class="feature-icon">📦</div>
        <h3>Matérias-Primas</h3>
        <p>Gerencie seu inventário de forma inteligente e eficiente</p>
        <router-link to="/raw-materials" class="feature-btn">Acessar →</router-link>
      </div>
      <div class="feature-card">
        <div class="feature-icon">🎯</div>
        <h3>Produtos</h3>
        <p>Defina produtos e suas composições com facilidade</p>
        <router-link to="/products" class="feature-btn">Acessar →</router-link>
      </div>
      <div class="feature-card featured">
        <div class="feature-icon">📊</div>
        <h3>Otimização</h3>
        <p>Calcule a produção ótima para maximizar receita</p>
        <router-link to="/optimization" class="feature-btn">Começar →</router-link>
      </div>
    </div>

    <div class="stats">
      <div class="stat-card">
        <div class="stat-number">∞</div>
        <div class="stat-label">Possibilidades</div>
      </div>
      <div class="stat-card">
        <div class="stat-number">100%</div>
        <div class="stat-label">Precisão</div>
      </div>
      <div class="stat-card">
        <div class="stat-number">⚡</div>
        <div class="stat-label">Rápido</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.home {
  max-width: 1400px;
  margin: 0 auto;
  padding: 3rem 2rem;
}

.hero {
  text-align: center;
  margin-bottom: 4rem;
  animation: fadeIn 0.8s ease;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}

.hero-title {
  font-size: 3.5rem;
  margin-bottom: 1rem;
  font-weight: 700;
}

.gradient-text {
  background: linear-gradient(135deg, #fff 0%, #f0f0f0 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-subtitle {
  font-size: 1.3rem;
  color: rgba(255, 255, 255, 0.9);
  font-weight: 300;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin-bottom: 4rem;
}

.feature-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  padding: 2.5rem;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  animation: slideUp 0.6s ease;
}

@keyframes slideUp {
  from { opacity: 0; transform: translateY(30px); }
  to { opacity: 1; transform: translateY(0); }
}

.feature-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 20px 60px rgba(102, 126, 234, 0.3);
}

.feature-card.featured {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.feature-card.featured h3,
.feature-card.featured p {
  color: white;
}

.feature-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.feature-card h3 {
  color: #2c3e50;
  margin-bottom: 0.75rem;
  font-size: 1.5rem;
}

.feature-card p {
  color: #64748b;
  line-height: 1.6;
  margin-bottom: 1.5rem;
}

.feature-btn {
  display: inline-block;
  padding: 0.75rem 1.5rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  text-decoration: none;
  border-radius: 10px;
  font-weight: 600;
  transition: all 0.3s ease;
}

.featured .feature-btn {
  background: white;
  color: #667eea;
}

.feature-btn:hover {
  transform: scale(1.05);
  box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
}

.stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 2rem;
}

.stat-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  padding: 2rem;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
}

.stat-number {
  font-size: 3rem;
  font-weight: 700;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 0.5rem;
}

.stat-label {
  color: #64748b;
  font-weight: 500;
}
</style>
