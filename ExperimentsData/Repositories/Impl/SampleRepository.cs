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
        
        private DataContext _context;

        public SampleRepository(DataContext context)
        {
            _context = context;
        }

        public List<SampleGroupedEntity> GetAllGrouped(Guid datasetGuid)
        {
            var result = _context.Samples
                .Include(x => x.Attributes)
                .Where(x=>x.DatasetEntityId == datasetGuid)
                .GroupBy(x =>new
                {
                    ExtractorType = x.ExtractorType,
                    OriginalFileName = x.OriginalFileName,
                    order = x.order,
                    label = x.label,
                    DatasetEntityId = x.DatasetEntityId,
                    Attributes = x.Attributes.Count(),
                    Id = x.Id
                })
                .AsNoTracking()
                .Select(x=> new SampleGroupedEntity(x.Key))
                .ToList();
            return result;
        }

        public void Save(SampleEntity map)
        {
//            var dataset = _context.Datasets
//                .Include(x=>x.Samples).First(x => x.Id == map.DatasetEntityId);
//            dataset.Samples.Add(map);
            _context.Samples.Add(map);
            _context.Entry(map).State = EntityState.Added;
            _context.SaveChanges();
        }

        public object GetById(Guid sampleId)
        {
            return _context.Samples
                .Include(x=>x.Attributes)
                .Where(x => x.Id.Equals(sampleId))
                .FirstOrDefault();
        }
    }
}