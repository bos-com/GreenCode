import React, { useState } from 'react';
import {
  MagnifyingGlassIcon,
  PlusIcon,
  FunnelIcon
} from '@heroicons/react/24/outline';

const Projects: React.FC = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filterStatus, setFilterStatus] = useState('all');

  const projects = [
    {
      id: 1,
      name: 'Solar Panel Installation',
      description: 'Installing solar panels on community buildings to reduce carbon footprint',
      category: 'Renewable Energy',
      status: 'In Progress',
      startDate: '2024-01-15',
      endDate: '2024-06-30',
      budget: '$50,000',
      manager: 'John Doe',
      impactScore: 8,
    },
    {
      id: 2,
      name: 'Community Garden',
      description: 'Creating a sustainable community garden for local food production',
      category: 'Agriculture',
      status: 'Completed',
      startDate: '2023-03-01',
      endDate: '2023-08-31',
      budget: '$15,000',
      manager: 'Jane Smith',
      impactScore: 9,
    },
    {
      id: 3,
      name: 'Water Conservation System',
      description: 'Implementing rainwater harvesting and greywater recycling systems',
      category: 'Water Conservation',
      status: 'Planning',
      startDate: '2024-04-01',
      endDate: '2024-12-31',
      budget: '$75,000',
      manager: 'Mike Johnson',
      impactScore: 7,
    },
    {
      id: 4,
      name: 'Recycling Program',
      description: 'Establishing comprehensive recycling and waste management program',
      category: 'Waste Management',
      status: 'In Progress',
      startDate: '2024-02-01',
      endDate: '2024-07-31',
      budget: '$25,000',
      manager: 'Sarah Wilson',
      impactScore: 6,
    },
  ];

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

  const filteredProjects = projects.filter(project => {
    const matchesSearch = project.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         project.description.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesFilter = filterStatus === 'all' || project.status === filterStatus;
    return matchesSearch && matchesFilter;
  });

  return (
    <div>
      <div className="mb-8">
        <div className="flex justify-between items-center">
          <div>
            <h1 className="text-3xl font-bold text-gray-900">Environmental Projects</h1>
            <p className="mt-2 text-gray-600">Manage and track your environmental sustainability projects</p>
          </div>
          <button className="btn-primary flex items-center">
            <PlusIcon className="h-5 w-5 mr-2" />
            New Project
          </button>
        </div>
      </div>

      {/* Search and Filter */}
      <div className="bg-white shadow rounded-lg p-4 mb-6">
        <div className="flex flex-col sm:flex-row gap-4">
          <div className="flex-1">
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                <MagnifyingGlassIcon className="h-5 w-5 text-gray-400" />
              </div>
              <input
                type="text"
                className="input-field pl-10"
                placeholder="Search projects..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
          </div>
          <div className="flex items-center space-x-2">
            <FunnelIcon className="h-5 w-5 text-gray-400" />
            <select
              className="input-field"
              value={filterStatus}
              onChange={(e) => setFilterStatus(e.target.value)}
            >
              <option value="all">All Status</option>
              <option value="Planning">Planning</option>
              <option value="In Progress">In Progress</option>
              <option value="Completed">Completed</option>
              <option value="On Hold">On Hold</option>
            </select>
          </div>
        </div>
      </div>

      {/* Projects Grid */}
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        {filteredProjects.map((project) => (
          <div key={project.id} className="card">
            <div className="flex items-start justify-between mb-4">
              <div>
                <h3 className="text-lg font-semibold text-gray-900">{project.name}</h3>
                <p className="text-sm text-gray-500">{project.category}</p>
              </div>
              <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${getStatusColor(project.status)}`}>
                {project.status}
              </span>
            </div>
            
            <p className="text-gray-600 mb-4 line-clamp-2">{project.description}</p>
            
            <div className="space-y-2 text-sm">
              <div className="flex justify-between">
                <span className="text-gray-500">Budget:</span>
                <span className="font-medium">{project.budget}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-500">Manager:</span>
                <span className="font-medium">{project.manager}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-500">Impact Score:</span>
                <div className="flex items-center">
                  <span className="font-medium mr-1">{project.impactScore}</span>
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
              </div>
            </div>
            
            <div className="mt-4 pt-4 border-t border-gray-200">
              <a
                href={`/projects/${project.id}`}
                className="text-green-600 hover:text-green-800 font-medium text-sm"
              >
                View Details →
              </a>
            </div>
          </div>
        ))}
      </div>

      {filteredProjects.length === 0 && (
        <div className="text-center py-12">
          <div className="text-gray-500">
            <MagnifyingGlassIcon className="h-12 w-12 mx-auto mb-4 text-gray-400" />
            <h3 className="text-lg font-medium text-gray-900 mb-2">No projects found</h3>
            <p>Try adjusting your search or filter criteria</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default Projects;
