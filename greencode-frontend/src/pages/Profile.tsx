import React, { useState } from 'react';
import { useAuth } from '../contexts/AuthContext';
import {
  UserIcon,
  EnvelopeIcon,
  ShieldCheckIcon,
  PencilIcon,
  CameraIcon
} from '@heroicons/react/24/outline';

const Profile: React.FC = () => {
  const { user } = useAuth();
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    firstName: user?.firstName || '',
    lastName: user?.lastName || '',
    email: user?.email || '',
    username: user?.username || '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle profile update
    console.log('Updating profile:', formData);
    setIsEditing(false);
  };

  const userStats = [
    { label: 'Projects Managed', value: '12' },
    { label: 'Tasks Completed', value: '48' },
    { label: 'Impact Score', value: '8.5' },
    { label: 'Member Since', value: 'Jan 2024' },
  ];

  return (
    <div>
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900">Profile</h1>
        <p className="mt-2 text-gray-600">Manage your personal information and account settings</p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Left Column - Profile Info */}
        <div className="lg:col-span-2 space-y-6">
          {/* Profile Card */}
          <div className="card">
            <div className="flex items-center justify-between mb-6">
              <h2 className="text-xl font-semibold text-gray-900">Profile Information</h2>
              <button
                onClick={() => setIsEditing(!isEditing)}
                className="flex items-center text-green-600 hover:text-green-800"
              >
                <PencilIcon className="h-4 w-4 mr-2" />
                {isEditing ? 'Cancel' : 'Edit'}
              </button>
            </div>

            <form onSubmit={handleSubmit} className="space-y-6">
              {/* Avatar */}
              <div className="flex items-center space-x-6">
                <div className="relative">
                  <div className="w-24 h-24 bg-green-100 rounded-full flex items-center justify-center">
                    <UserIcon className="h-12 w-12 text-green-600" />
                  </div>
                  {isEditing && (
                    <button
                      type="button"
                      className="absolute bottom-0 right-0 bg-green-600 text-white p-2 rounded-full hover:bg-green-700"
                    >
                      <CameraIcon className="h-4 w-4" />
                    </button>
                  )}
                </div>
                <div>
                  <h3 className="text-lg font-medium text-gray-900">{user?.username}</h3>
                  <p className="text-sm text-gray-500">{user?.role}</p>
                </div>
              </div>

              {/* Form Fields */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label htmlFor="firstName" className="block text-sm font-medium text-gray-700 mb-1">
                    First Name
                  </label>
                  <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className={`input-field ${!isEditing ? 'bg-gray-50' : ''}`}
                  />
                </div>
                <div>
                  <label htmlFor="lastName" className="block text-sm font-medium text-gray-700 mb-1">
                    Last Name
                  </label>
                  <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className={`input-field ${!isEditing ? 'bg-gray-50' : ''}`}
                  />
                </div>
                <div>
                  <label htmlFor="username" className="block text-sm font-medium text-gray-700 mb-1">
                    Username
                  </label>
                  <input
                    type="text"
                    id="username"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className={`input-field ${!isEditing ? 'bg-gray-50' : ''}`}
                  />
                </div>
                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-1">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className={`input-field ${!isEditing ? 'bg-gray-50' : ''}`}
                  />
                </div>
              </div>

              {isEditing && (
                <div className="flex justify-end space-x-3">
                  <button
                    type="button"
                    onClick={() => setIsEditing(false)}
                    className="btn-secondary"
                  >
                    Cancel
                  </button>
                  <button type="submit" className="btn-primary">
                    Save Changes
                  </button>
                </div>
              )}
            </form>
          </div>

          {/* Account Settings */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Account Settings</h2>
            <div className="space-y-4">
              <div className="flex items-center justify-between py-3 border-b border-gray-200">
                <div className="flex items-center space-x-3">
                  <EnvelopeIcon className="h-5 w-5 text-gray-400" />
                  <div>
                    <p className="font-medium text-gray-900">Email Notifications</p>
                    <p className="text-sm text-gray-500">Receive email updates about your projects</p>
                  </div>
                </div>
                <button className="relative inline-flex flex-shrink-0 h-6 w-11 border-2 border-transparent rounded-full cursor-pointer bg-green-600 transition-colors ease-in-out duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500">
                  <span className="translate-x-5 inline-block h-5 w-5 rounded-full bg-white shadow transform ring-0 transition ease-in-out duration-200"></span>
                </button>
              </div>
              
              <div className="flex items-center justify-between py-3 border-b border-gray-200">
                <div className="flex items-center space-x-3">
                  <ShieldCheckIcon className="h-5 w-5 text-gray-400" />
                  <div>
                    <p className="font-medium text-gray-900">Two-Factor Authentication</p>
                    <p className="text-sm text-gray-500">Add an extra layer of security to your account</p>
                  </div>
                </div>
                <button className="text-green-600 hover:text-green-800 font-medium text-sm">
                  Enable
                </button>
              </div>
            </div>
          </div>
        </div>

        {/* Right Column - Stats */}
        <div className="space-y-6">
          {/* User Stats */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Your Impact</h2>
            <div className="grid grid-cols-2 gap-4">
              {userStats.map((stat, index) => (
                <div key={index} className="text-center">
                  <div className="text-2xl font-bold text-green-600">{stat.value}</div>
                  <div className="text-sm text-gray-500">{stat.label}</div>
                </div>
              ))}
            </div>
          </div>

          {/* Quick Actions */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Quick Actions</h2>
            <div className="space-y-3">
              <button className="w-full text-left px-4 py-3 bg-gray-50 rounded-lg hover:bg-gray-100 transition-colors">
                <div className="font-medium text-gray-900">Change Password</div>
                <div className="text-sm text-gray-500">Update your account password</div>
              </button>
              <button className="w-full text-left px-4 py-3 bg-gray-50 rounded-lg hover:bg-gray-100 transition-colors">
                <div className="font-medium text-gray-900">Download Data</div>
                <div className="text-sm text-gray-500">Get a copy of your personal data</div>
              </button>
              <button className="w-full text-left px-4 py-3 bg-red-50 rounded-lg hover:bg-red-100 transition-colors">
                <div className="font-medium text-red-600">Delete Account</div>
                <div className="text-sm text-red-500">Permanently delete your account</div>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
