import React, { useState } from 'react';
import api from '../api';

function Login() {
  const [email, setEmail]       = useState('');
  const [password, setPassword] = useState('');
  const [error, setError]       = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const response = await api.post('/auth/login', { email, password });
      localStorage.setItem('token', response.data.token);
      window.location.href = '/';
    } catch (err) {
      setError('Invalid email or password. Please try again.');
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '5rem auto', padding: '2rem',
                  border: '1px solid #ccc', borderRadius: '8px', fontFamily: 'Arial' }}>
      <h2 style={{ color: '#2d6a4f' }}>Login to GreenCode</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleLogin}>
        <div style={{ marginBottom: '1rem' }}>
          <label>Email</label><br />
          <input type="email" value={email} onChange={e => setEmail(e.target.value)}
            required style={{ width: '100%', padding: '0.5rem', marginTop: '0.25rem' }} />
        </div>
        <div style={{ marginBottom: '1rem' }}>
          <label>Password</label><br />
          <input type="password" value={password} onChange={e => setPassword(e.target.value)}
            required style={{ width: '100%', padding: '0.5rem', marginTop: '0.25rem' }} />
        </div>
        <button type="submit" style={{ background: '#2d6a4f', color: 'white',
          padding: '0.6rem 1.5rem', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
          Login
        </button>
        <p style={{ marginTop: '1rem' }}>
          <a href="/forgot-password" style={{ color: '#2d6a4f' }}>Forgot password?</a>
        </p>
      </form>
    </div>
  );
}

export default Login;