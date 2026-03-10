<template>
  <nav class="navbar">
    <div class="nav-container">
      <div class="nav-brand">
        <span class="icon">🏭</span>
        <span>Otimizador de Produção</span>
      </div>
      <div class="nav-links">
        <router-link to="/" class="nav-link">
          <span class="link-icon">🏠</span>
          <span>Início</span>
        </router-link>
        <router-link to="/raw-materials" class="nav-link">
          <span class="link-icon">📦</span>
          <span>Matérias-Primas</span>
        </router-link>
        <router-link to="/products" class="nav-link">
          <span class="link-icon">🎯</span>
          <span>Produtos</span>
        </router-link>
        <router-link to="/optimization" class="nav-link">
          <span class="link-icon">📊</span>
          <span>Otimização</span>
        </router-link>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.nav-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.nav-brand {
  font-size: 1.5rem;
  font-weight: 700;
  color: #667eea;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.icon {
  font-size: 1.8rem;
}

.nav-links {
  display: flex;
  gap: 0.5rem;
}

.nav-link {
  color: #4a5568;
  text-decoration: none;
  padding: 0.75rem 1.25rem;
  border-radius: 12px;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-weight: 500;
  transition: all 0.3s ease;
}

.link-icon {
  font-size: 1.2rem;
}

.nav-link:hover {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);
}

.nav-link.router-link-active {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);
}
</style>
