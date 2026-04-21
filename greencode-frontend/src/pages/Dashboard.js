import React from 'react';

function Dashboard() {
  return (
    <div style={{ padding: '2rem', fontFamily: 'Arial, sans-serif' }}>
      <h1 style={{ color: '#2d6a4f' }}>GreenCode Dashboard</h1>
      <p>Welcome to the GreenCode sustainability platform.</p>
      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(3, 1fr)',
        gap: '1rem',
        marginTop: '2rem'
      }}>
        <div style={{ background: '#d8f3dc', padding: '1rem', borderRadius: '8px' }}>
          <h3>Projects</h3>
          <p>Manage sustainability projects</p>
        </div>
        <div style={{ background: '#b7e4c7', padding: '1rem', borderRadius: '8px' }}>
          <h3>Reports</h3>
          <p>View environmental reports</p>
        </div>
        <div style={{ background: '#95d5b2', padding: '1rem', borderRadius: '8px' }}>
          <h3>Community</h3>
          <p>Connect with others</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
