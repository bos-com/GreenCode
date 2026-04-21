import React, { useState } from 'react';
import api from '../api';

function ForgotPassword() {
  const [email, setEmail]     = useState('');
  const [message, setMessage] = useState('');
  const [error, setError]     = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');
    setError('');

    try {
      await api.post('/auth/forgot-password', { email });
      setMessage(
        'Password reset email sent! Please check your inbox and follow the instructions.'
      );
      setEmail('');
    } catch (err) {
      setError(
        err.response?.data?.message ||
        'Something went wrong. Please try again later.'
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '5rem auto', padding: '2rem',
                  border: '1px solid #ccc', borderRadius: '8px', fontFamily: 'Arial' }}>
      <h2 style={{ color: '#2d6a4f' }}>Forgot Password</h2>
      <p style={{ color: '#555' }}>
        Enter your email address and we will send you a link to reset your password.
      </p>

      {message && (
        <div style={{ background: '#d8f3dc', padding: '0.75rem',
                      borderRadius: '4px', marginBottom: '1rem', color: '#1b4332' }}>
          {message}
        </div>
      )}

      {error && (
        <div style={{ background: '#ffe0e0', padding: '0.75rem',
                      borderRadius: '4px', marginBottom: '1rem', color: '#7f1d1d' }}>
          {error}
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '1rem' }}>
          <label>Email Address</label><br />
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            placeholder="you@example.com"
            style={{ width: '100%', padding: '0.5rem', marginTop: '0.25rem',
                     borderRadius: '4px', border: '1px solid #ccc' }}
          />
        </div>

        <button
          type="submit"
          disabled={loading}
          style={{ background: loading ? '#888' : '#2d6a4f', color: 'white',
                   padding: '0.6rem 1.5rem', border: 'none',
                   borderRadius: '4px', cursor: loading ? 'not-allowed' : 'pointer',
                   width: '100%' }}>
          {loading ? 'Sending...' : 'Send Reset Link'}
        </button>

        <p style={{ marginTop: '1rem', textAlign: 'center' }}>
          <a href="/login" style={{ color: '#2d6a4f' }}>Back to Login</a>
        </p>
      </form>
    </div>
  );
}

export default ForgotPassword;