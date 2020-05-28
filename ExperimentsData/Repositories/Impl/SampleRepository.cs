using System;
using System.Collections.Generic;
using System.Linq;
using ExperimentsData.Models;
using ExperimentsData.Models.DAO;
using Microsoft.EntityFrameworkCore;

namespace ExperimentsData.Repositories.Impl
{
    public class SampleRepository : ISampleRepository
    {
        
        private readonly DataContext _context;

        public SampleRepository(DataContext context)
        {
            _context = context;
        }

        public List<SampleGroupedEntity> GetAllGrouped(Guid datasetGuid)
        {
            var result = _context.Samples
                .Include(x => x.Attributes)
                .Where(x=>x.DatasetEntityId == datasetGuid)
                .Select(x=> new SampleGroupedEntity(x))
                .ToList();

            return result;
        }

        public void Save(SampleEntity map)
        {
            _context.Samples.Add(map);
            _context.Entry(map).State = EntityState.Added;
            _context.SaveChanges();
        }

        public SampleEntity GetById(Guid sampleId)
        {
            return _context.Samples
                .Include(x=>x.Attributes)
                .Where(x => x.Id.Equals(sampleId))
                .FirstOrDefault();
        }

        public SampleEntity GetDatabaseIdAndFileName(Guid datasetGuid, string fileName)
        {
            return _context.Samples
                .Include(x=>x.Attributes)
                .Where(x => x.DatasetEntityId.Equals(datasetGuid) && x.OriginalFileName.Equals(fileName))
                .FirstOrDefault();
            
        }

        public SampleEntity DeleteById(Guid guid)
        {
            var entity = this.GetById(guid);
            _context.Samples.Remove(entity);
            _context.SaveChanges();
            return entity;
        }
    }
}