using System;
using System.Collections.Generic;
using System.Linq;
using ExperimentsData.Models;
using ExperimentsData.Models.DAO;
using Microsoft.EntityFrameworkCore;

namespace ExperimentsData.Repositories.Impl
{
    public class DatasetRepository : IDatasetRepository
    {
        private DataContext _context;

        public DatasetRepository(DataContext context)
        {
            _context = context;
        }

        public List<DatasetGroupedEntity> GetAllGrouped()
        {
            
            var result = _context.Datasets
                .Include(x => x.Samples)
                .Select(x=> new DatasetGroupedEntity(x))
                .ToList();
            return result;
        }

        public void Save(DatasetEntity datasetEntity)
        {
            _context.Datasets.Add(datasetEntity);
            _context.Entry(datasetEntity).State = EntityState.Added;
            _context.SaveChanges();
        }

        public DatasetEntity GetById(Guid guid)
        {
            return _context.Datasets
                .Include(x=>x.Samples).ThenInclude(x=>x.Attributes)
                .FirstOrDefault(x => x.Id.Equals(guid));
        }

        public DatasetEntity GetByName(string name)
        {
            return _context.Datasets
                .Include(x=>x.Samples)
                .FirstOrDefault(x => x.name.Equals(name));
        }

        public DatasetEntity DeleteById(Guid guid)
        {
            var entity = this.GetById(guid);
            _context.Datasets.Remove(entity);
            _context.SaveChanges();
            return entity;
        }
    }
}