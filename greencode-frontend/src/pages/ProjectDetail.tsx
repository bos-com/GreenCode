import React from 'react';
import { useParams } from 'react-router-dom';
import {
  ArrowLeftIcon,
  CalendarIcon,
  CurrencyDollarIcon,
  UserIcon,
  MapPinIcon,
  ChartBarIcon
} from '@heroicons/react/24/outline';

const ProjectDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();

  // Mock project data - in real app, this would come from API
  const project = {
    id: parseInt(id || '1'),
    name: 'Solar Panel Installation',
    description: 'Installing solar panels on community buildings to reduce carbon footprint and promote renewable energy usage. This project aims to install 500 solar panels across 10 community buildings, reducing CO2 emissions by approximately 200 tons per year.',
    category: 'Renewable Energy',
    status: 'In Progress',
    startDate: '2024-01-15',
    endDate: '2024-06-30',
    budget: '$50,000',
    actualCost: '$35,000',
    location: 'Community Center, Downtown',
    coordinates: '40.7128, -74.0060',
    manager: {
      name: 'John Doe',
      email: 'john.doe@greencode.com',
      role: 'Project Manager'
    },
    teamSize: 8,
    impactScore: 8,
    sustainabilityRating: 4,
    isPublic: true,
    milestones: [
      { id: 1, title: 'Site Assessment', completed: true, date: '2024-01-20' },
      { id: 2, title: 'Permit Acquisition', completed: true, date: '2024-02-15' },
      { id: 3, title: 'Equipment Procurement', completed: true, date: '2024-03-10' },
      { id: 4, title: 'Installation Phase 1', completed: true, date: '2024-04-05' },
      { id: 5, title: 'Installation Phase 2', completed: false, date: '2024-05-15' },
      { id: 6, title: 'Final Inspection', completed: false, date: '2024-06-25' },
    ],
    environmentalImpact: {
      co2Reduction: '200 tons/year',
      energySavings: '75,000 kWh/year',
      costSavings: '$12,000/year',
      treesEquivalent: '3,000 trees'
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'Completed':
        return 'bg-green-100 text-green-800';
      case 'In Progress':
        return 'bg-blue-100 text-blue-800';
      case 'Planning':
        return 'bg-yellow-100 text-yellow-800';
      case 'On Hold':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  const completionPercentage = Math.round(
    (project.milestones.filter(m => m.completed).length / project.milestones.length) * 100
  );

  return (
    <div>
      {/* Header */}
      <div className="mb-6">
        <a href="/projects" className="inline-flex items-center text-green-600 hover:text-green-800 mb-4">
          <ArrowLeftIcon className="h-4 w-4 mr-2" />
          Back to Projects
        </a>
        <div className="flex justify-between items-start">
          <div>
            <h1 className="text-3xl font-bold text-gray-900">{project.name}</h1>
            <p className="mt-2 text-gray-600">{project.description}</p>
          </div>
          <span className={`inline-flex px-3 py-1 text-sm font-semibold rounded-full ${getStatusColor(project.status)}`}>
            {project.status}
          </span>
        </div>
      </div>

      {/* Main Content Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Left Column - Project Info */}
        <div className="lg:col-span-2 space-y-6">
          {/* Project Details */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Project Details</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="flex items-center space-x-3">
                <CalendarIcon className="h-5 w-5 text-gray-400" />
                <div>
                  <p className="text-sm text-gray-500">Start Date</p>
                  <p className="font-medium">{project.startDate}</p>
                </div>
              </div>
              <div className="flex items-center space-x-3">
                <CalendarIcon className="h-5 w-5 text-gray-400" />
                <div>
                  <p className="text-sm text-gray-500">End Date</p>
                  <p className="font-medium">{project.endDate}</p>
                </div>
              </div>
              <div className="flex items-center space-x-3">
                <CurrencyDollarIcon className="h-5 w-5 text-gray-400" />
                <div>
                  <p className="text-sm text-gray-500">Budget</p>
                  <p className="font-medium">{project.budget}</p>
                </div>
              </div>
              <div className="flex items-center space-x-3">
                <CurrencyDollarIcon className="h-5 w-5 text-gray-400" />
                <div>
                  <p className="text-sm text-gray-500">Actual Cost</p>
                  <p className="font-medium">{project.actualCost}</p>
                </div>
              </div>
              <div className="flex items-center space-x-3">
                <MapPinIcon className="h-5 w-5 text-gray-400" />
                <div>
                  <p className="text-sm text-gray-500">Location</p>
                  <p className="font-medium">{project.location}</p>
                </div>
              </div>
              <div className="flex items-center space-x-3">
                <UserIcon className="h-5 w-5 text-gray-400" />
                <div>
                  <p className="text-sm text-gray-500">Team Size</p>
                  <p className="font-medium">{project.teamSize} members</p>
                </div>
              </div>
            </div>
          </div>

          {/* Progress */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Project Progress</h2>
            <div className="mb-4">
              <div className="flex justify-between items-center mb-2">
                <span className="text-sm font-medium text-gray-700">Completion</span>
                <span className="text-sm font-medium text-gray-700">{completionPercentage}%</span>
              </div>
              <div className="w-full bg-gray-200 rounded-full h-3">
                <div
                  className="bg-green-600 h-3 rounded-full transition-all duration-300"
                  style={{ width: `${completionPercentage}%` }}
                ></div>
              </div>
            </div>
            
            <h3 className="text-lg font-medium text-gray-900 mb-3">Milestones</h3>
            <div className="space-y-3">
              {project.milestones.map((milestone) => (
                <div key={milestone.id} className="flex items-center space-x-3">
                  <div className={`flex-shrink-0 w-5 h-5 rounded-full border-2 ${
                    milestone.completed
                      ? 'bg-green-500 border-green-500'
                      : 'bg-white border-gray-300'
                  }`}>
                    {milestone.completed && (
                      <svg className="w-3 h-3 text-white mx-auto" fill="currentColor" viewBox="0 0 20 20">
                        <path fillRule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clipRule="evenodd" />
                      </svg>
                    )}
                  </div>
                  <div className="flex-1">
                    <p className={`font-medium ${milestone.completed ? 'text-gray-900' : 'text-gray-500'}`}>
                      {milestone.title}
                    </p>
                    <p className="text-sm text-gray-500">{milestone.date}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Right Column - Sidebar */}
        <div className="space-y-6">
          {/* Manager Info */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Project Manager</h2>
            <div className="text-center">
              <div className="w-16 h-16 bg-green-100 rounded-full mx-auto mb-3 flex items-center justify-center">
                <UserIcon className="h-8 w-8 text-green-600" />
              </div>
              <h3 className="font-medium text-gray-900">{project.manager.name}</h3>
              <p className="text-sm text-gray-500">{project.manager.role}</p>
              <p className="text-sm text-gray-600 mt-2">{project.manager.email}</p>
            </div>
          </div>

          {/* Impact Metrics */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Environmental Impact</h2>
            <div className="space-y-3">
              <div className="flex justify-between items-center">
                <span className="text-sm text-gray-600">CO₂ Reduction</span>
                <span className="font-medium text-green-600">{project.environmentalImpact.co2Reduction}</span>
              </div>
              <div className="flex justify-between items-center">
                <span className="text-sm text-gray-600">Energy Savings</span>
                <span className="font-medium text-green-600">{project.environmentalImpact.energySavings}</span>
              </div>
              <div className="flex justify-between items-center">
                <span className="text-sm text-gray-600">Cost Savings</span>
                <span className="font-medium text-green-600">{project.environmentalImpact.costSavings}</span>
              </div>
              <div className="flex justify-between items-center">
                <span className="text-sm text-gray-600">Trees Equivalent</span>
                <span className="font-medium text-green-600">{project.environmentalImpact.treesEquivalent}</span>
              </div>
            </div>
          </div>

          {/* Ratings */}
          <div className="card">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Ratings</h2>
            <div className="space-y-3">
              <div>
                <div className="flex justify-between items-center mb-1">
                  <span className="text-sm text-gray-600">Impact Score</span>
                  <span className="font-medium">{project.impactScore}/10</span>
                </div>
                <div className="flex">
                  {[...Array(5)].map((_, i) => (
                    <svg
                      key={i}
                      className={`h-4 w-4 ${i < project.impactScore / 2 ? 'text-yellow-400' : 'text-gray-300'}`}
                      fill="currentColor"
                      viewBox="0 0 20 20"
                    >
                      <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                    </svg>
                  ))}
                </div>
              </div>
              <div>
                <div className="flex justify-between items-center mb-1">
                  <span className="text-sm text-gray-600">Sustainability</span>
                  <span className="font-medium">{project.sustainabilityRating}/5</span>
                </div>
                <div className="flex">
                  {[...Array(5)].map((_, i) => (
                    <svg
                      key={i}
                      className={`h-4 w-4 ${i < project.sustainabilityRating ? 'text-green-400' : 'text-gray-300'}`}
                      fill="currentColor"
                      viewBox="0 0 20 20"
                    >
                      <path fillRule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clipRule="evenodd" />
                    </svg>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProjectDetail;
